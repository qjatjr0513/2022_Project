import os
import time
import threading
import requests
import json

serverToken = 'AAAAHIMfagk:APA91bGnQU5u0dBVeItGS64CZS7fyflCL4cForUOGB5pHhgNhUot41xAnx_ttfEFp6IBau5_y-Q-CCGnW9Z4k7x1PXMbXh8WgMUhaDilljaiMZiwgacGB30sQBWtTPAeOGQ2CAfjjLxd'
deviceToken = ' f176g2aHTjaDvAyxHEOMXE:APA91bH7A4j6tWl7IxgpY3Jb4obsAThHdgAfG3fHjdDlZGmbNkwAHpB4v82H278aKFZ0ozHfqT8kEsWka1ky7mmjkDvJL01-lIXkvNnOTgxaUs8CJbeH_8Eb3BBfaaQTF2f_dyUN-e5D'

def fcm2():
                    headers = {
                        'Content-Type': 'application/json',
                        'Authorization': 'key=' + serverToken,
                    }

                    body = {
                            'notification': {'title': '알림!!!',
                                                'body': '환자가 돌아왔습니다!!'
                                                },
                            'to':
                                deviceToken,
                            'priority': 'high',
                            #   'data': dataPayLoad,
                            }
                    response = requests.post("https://fcm.googleapis.com/fcm/send",headers = headers, data=json.dumps(body))
                    print(response.status_code)

                    print(response.json())




