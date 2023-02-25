# 치매환자 무단외출 알림이(졸업작품)
## 프로젝트 기획의도
현대 사회는 평균수명의 증가에 따라 총인구 중에 차지하는 고령자(노인)의 인구 비율이 점차로 많아지는 인구 고령화로 인해 점점 치매 환자의 비율이 매년 높아지고 있으며, 노인 10명 중 1명꼴로 치매가 걸린다고 한다. 치매를 치료하기 위한 연구가 계속되고 있지만 현재는 치매를 완치할 방법이 없다. 치매가 걸린 환자의 주요증상 중 무단외출은 보호자가 가장 두려워하는 증상이며 환자에게 가장 치명적일 수 있는 증상이다. 이러한 무단외출을 방지하기 위해 라즈베리파이를 통해 openCV을 활용한 얼굴인식과 LoRa 통신을 활용한 GPS를 통해 환자의 무단외출을 사전에 감지하고 휴대전화 애플리케이션에서 실시간으로 위치를 파악할 수 있는 IoT 기술을 개발하였다.
## 시스템 구성
<table>
  <tbody>
  <td><img src="https://user-images.githubusercontent.com/80880587/221352555-67cd99a1-fe15-41a5-86f5-72525800ad2d.png"  width="100px" height="70px"/></td>
  <td>라즈베리파이3 b+</td>
  </tbody>
  <tbody>
  <td><img src="https://user-images.githubusercontent.com/80880587/221352639-b1d1d871-8796-4a4d-aed7-97ca11e53d87.png"  width="100px" height="70px"/></td>
  <td>TTGO LoRa T-Beam V1.0 보드</td>
  </tbody>
  <tbody>
  <td><img src="https://user-images.githubusercontent.com/80880587/221352682-03724f50-55be-42df-acd9-3bf07ae58e00.png"  width="100px" height="70px"/></td>
  <td>라즈베리파이 카메라모듈 V2, 8MP</td>
  </tbody>
  <tbody>
  <td><img src="https://user-images.githubusercontent.com/80880587/221352698-bb939c91-aabf-4166-9c4c-8b6a6a0ccde3.png"  width="100px" height="70px"/></td>
  <td>디지털 접촉식 터치센서 [DFR0030]</td>
  </tbody>
  <tbody>
  <td><img src="https://user-images.githubusercontent.com/80880587/221352707-c5b04ea6-3fdd-42dd-9edd-6d33d9b2b423.png"  width="100px" height="70px"/></td>
  <td>GP2Y0A21YK0F 적외선 거리 측정 센서</td>
  </tbody>
</table>

[![Video Label](http://img.youtube.com/vi/-bFfsAX0tb0/maxresdefault.jpg)](https://youtu.be/-bFfsAX0tb0)
