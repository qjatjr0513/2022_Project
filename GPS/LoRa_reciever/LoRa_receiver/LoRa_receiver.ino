#include <SPI.h>
#include <LoRa.h>       // https://github.com/sandeepmistry/arduino-LoRa
#include <U8g2lib.h>   // https://github.com/olikraus/U8g2_Arduino
#include "BluetoothSerial.h"

#define OFF 0   // For LED
#define ON 1

// SPI LoRa Radio
#define LORA_SCK 5        // GPIO5 - SX1276 SCK
#define LORA_MISO 19     // GPIO19 - SX1276 MISO
#define LORA_MOSI 27    // GPIO27 - SX1276 MOSI
#define LORA_CS 18     // GPIO18 - SX1276 CS
#define LORA_RST 14   // GPIO14 - SX1276 RST
#define LORA_IRQ 26  // GPIO26 - SX1276 IRQ (interrupt request)

// I2C OLED Display works with SSD1306 driver
#define OLED_SDA 4
#define OLED_SCL 15
#define OLED_RST 16

U8G2_SSD1306_128X64_NONAME_F_SW_I2C Display(U8G2_R0, /* clock=*/ OLED_SCL, /* data=*/ OLED_SDA, /* reset=*/ OLED_RST); // Full framebuffer, SW I2C
U8G2LOG u8g2log;

#define U8LOG_WIDTH 32
#define U8LOG_HEIGHT 10
#define BIG_FONT u8g2_font_ncenB10_tr
#define SMALL_FONT u8g2_font_tom_thumb_4x6_mf

#if !defined(CONFIG_BT_ENABLED) || !defined(CONFIG_BLUEDROID_ENABLED)
#error Bluetooth is not enabled! Please run `make menuconfig` to and enable it
#endif

BluetoothSerial SerialBT;
const int LoRaFrequency = 923000000; 

String rssi = "";
String packet = "";
// allocate memory for print buffer
uint8_t u8log_buffer[U8LOG_WIDTH*U8LOG_HEIGHT];

void clear_disp(){
  u8g2log.print("\f"); // crashes the processor some times...
  Display.setFont(BIG_FONT);
  Display.clearBuffer();  
  Display.setCursor(0,12); Display.print("LoRa ReceiverV2");
  Display.sendBuffer();
  Display.setFont(SMALL_FONT);
  delay(2000);

}
void setup() {
  Serial.begin(115200);
  while (!Serial);
  Serial.println("LoRa receiver");
  SerialBT.begin("ESP32test2"); //Bluetooth device name
  Serial.println("The device started, now you can pair it with bluetooth!");

  Display.begin();
  Display.enableUTF8Print();    // enable UTF8 support for the Arduino print() function
  Display.setFont(u8g2_font_tom_thumb_4x6_mf);  // set the font for the terminal window
  u8g2log.begin(Display, U8LOG_WIDTH, U8LOG_HEIGHT, u8log_buffer); // connect to u8g2, assign buffer
  u8g2log.setLineHeightOffset(0); // set extra space between lines in pixel, this can be negative
  u8g2log.setRedrawMode(0);   // 0: Update screen with newline, 1: Update screen for every char 
  clear_disp();

  SPI.begin(LORA_SCK, LORA_MISO, LORA_MOSI, LORA_CS);  
  LoRa.setPins(LORA_CS, LORA_RST, LORA_IRQ);         
  
  if (!LoRa.begin(LoRaFrequency)) {
    Serial.println("Starting LoRa failed!");
    while (1);
  }
  
  // The larger the spreading factor the greater the range but slower data rate
  // Send and receive radios need to be set the same
  LoRa.setSpreadingFactor(7);  // ranges from 6-12, default 7 see API docs

   Display.clearBuffer();  
   Display.setCursor(0,12); Display.print("LoRa ReceiverV2");
   Display.sendBuffer();
}

void loop() {
  // try to parse packet
  int packetSize = LoRa.parsePacket();
  if (packetSize) {   // received a packet
    packet = "";
    while (LoRa.available()) {     // read packet
      packet += (char)LoRa.read(); // Assemble new packet
    }
    rssi = LoRa.packetRssi();
    
    // print the received packet on the OLED. u8g2log emulates a term window & scrolls
    if (packet == "clearDisp") clear_disp();
    else {
      u8g2log.print(packet);
      u8g2log.print("\n");
    }
    Serial.print("{\'type\' : \'packet\', \'packet\': \'");
    Serial.println(packet + "\', \'rssi\' : \'" + rssi + "\' }");
    SerialBT.println(packet);
  }  // end of received a packet
}
