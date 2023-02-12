import RPi.GPIO as GPIO 
import time 
import os
import threading
import requests
import json
import fcm

tilt_pin = 27 #터치 센서의 경우 센서만 바꾸면 됨 
GPIO.setmode(GPIO.BCM) 
GPIO.setup(tilt_pin, GPIO.IN) 

try: 
    while True:
        if  GPIO.input(tilt_pin)==True:
           fcm.fcm2()

except KeyboardInterrupt:
    GPIO.cleanup()



