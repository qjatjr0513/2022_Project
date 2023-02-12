from time import sleep
from threading import Timer
from datetime import datetime
import time
import os
import requests
import json

serverToken = 'AAAAHIMfagk:APA91bGnQU5u0dBVeItGS64CZS7fyflCL4cForUOGB5pHhgNhUot41xAnx_ttfEFp6IBau5_y-Q-CCGnW9Z4k7x1PXMbXh8WgMUhaDilljaiMZiwgacGB30sQBWtTPAeOGQ2CAfjjLxd'
deviceToken = ' f176g2aHTjaDvAyxHEOMXE:APA91bH7A4j6tWl7IxgpY3Jb4obsAThHdgAfG3fHjdDlZGmbNkwAHpB4v82H278aKFZ0ozHfqT8kEsWka1ky7mmjkDvJL01-lIXkvNnOTgxaUs8CJbeH_8Eb3BBfaaQTF2f_dyUN-e5D'

class MyInfiniteTimer():

    def __init__(self, t, hFunction):
        self.t = t
        self.hFunction = hFunction
        self.thread = Timer(self.t, self.handle_function)
        
    def handle_function(self):
        self.hFunction()
        self.thread = Timer(self.t, self.handle_function)
        self.thread.start()
        
    def start(self):
        self.thread = Timer(self.t, self.handle_function)
        self.thread.start()
        
    def cancel(self):
        self.thread.cancel()

def fcm():
    headers = {
        'Content-Type': 'application/json',
        'Authorization': 'key=' + serverToken,
    }

    body = {
            'notification': {'title': '경고!!',
                                'body': '환자가 무단외출 하였습니다.'
                                },
            'to':
                deviceToken,
            'priority': 'high',
            #   'data': dataPayLoad,
            }
    response = requests.post("https://fcm.googleapis.com/fcm/send",headers = headers, data=json.dumps(body))
    print(response.json())

t = MyInfiniteTimer(2, fcm)
t.start()
time.sleep(5)
t.cancel()
time.sleep(5)
t.start()
time.sleep(5)
t.cancel()