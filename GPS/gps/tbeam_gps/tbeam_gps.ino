#include <SPI.h>
#include <LoRa.h>      
#include <TinyGPS++.h>                       



TinyGPSPlus gps;                            
// HardwareSerial Serial1(1); // Glacierjay had to comment this out to remove linker warning

                // SPI LoRa Radio
#define LORA_SCK 5        // GPIO5 - SX1276 SCK
#define LORA_MISO 19     // GPIO19 - SX1276 MISO
#define LORA_MOSI 27    // GPIO27 -  SX1276 MOSI
#define LORA_CS 18     // GPIO18 -   SX1276 CS
#define LORA_RST 14   // GPIO14 -    SX1276 RST
#define LORA_IRQ 26  // GPIO26 -     SX1276 IRQ (interrupt request)

const int blueLED = 14; // blue LED next to GPS -- blink when packet sent
int pktCount = 0; // count how many packets sent

#define BUTTON_PIN 39

void setup()
{
  Serial.begin(115200);
  Serial1.begin(9600, SERIAL_8N1, 34, 12);   //17-TX 18-RX for GPS
 
  
    // Very important for SPI pin & LoRa configuration!
  SPI.begin(LORA_SCK, LORA_MISO, LORA_MOSI, LORA_CS); 
  LoRa.setPins(LORA_CS, LORA_RST, LORA_IRQ);         

  pinMode(blueLED, OUTPUT); // For LED feedback
  pinMode(BUTTON_PIN, INPUT); // Middle button next to LoRa chip. The one on the right is RESET, careful...

  if (!LoRa.begin(923000000)) {
    Serial.println("Starting LoRa failed!");
    while (1); // if LoRa won't start just die with infinite loop
  }
  LoRa.setSpreadingFactor(7); // ranges from 6-12, default 7 see API docs. Changed for ver 0.1 Glacierjay
  LoRa.setTxPower(14, PA_OUTPUT_PA_BOOST_PIN);
  remote_clear_display(); // tell the receiver to clear the display
}

void remote_clear_display(){ // see ttgo_oled_rcv_lora_V0_3.ino
  LoRa.beginPacket();
  pktCount++;
  LoRa.print("clearDisp");
  LoRa.endPacket();
}

void print_info_json(){
  Serial.print("Latitude  : ");
  Serial.println(gps.location.lat(), 5);
  Serial.print("Longitude : ");
  Serial.println(gps.location.lng(), 4);
  Serial.print("Satellites: ");
  Serial.println(gps.satellites.value());
  Serial.print("Altitude  : ");
  Serial.print(gps.altitude.feet() / 3.2808);
  Serial.println("M");
  Serial.print("Time      : ");
  Serial.print(gps.time.hour());
  Serial.print(":");
  Serial.print(gps.time.minute());
  Serial.print(":");
  Serial.println(gps.time.second());
  Serial.print("Speed     : ");
  Serial.println(gps.speed.kmph()); 
  Serial.println("**********************");
  
}

void loop()
{
 
  print_info_json();
 // send LoRa packet
if (gps.location.isValid()){  // first couple of times through the loop, gps not yet available
  digitalWrite(blueLED, HIGH);  // Turn blue LED on
  LoRa.beginPacket();
  LoRa.print(gps.location.lat(),5);
  LoRa.print(",");
  LoRa.print(gps.location.lng(),4);
  LoRa.endPacket();
  digitalWrite(blueLED, LOW); // Turn blue LED off
}
else {
  LoRa.beginPacket();
  LoRa.print("Error: Invalid gps data");
  LoRa.endPacket();
}

  smartDelay(10000);                                      

  if (millis() > 5000 && gps.charsProcessed() < 10)
    Serial.println(F("No GPS data received: check wiring"));
}

static void smartDelay(unsigned long ms)                
{
  unsigned long start = millis();
  do
  {
    while (Serial1.available())
      gps.encode(Serial1.read());
  } while (millis() - start < ms);
}
