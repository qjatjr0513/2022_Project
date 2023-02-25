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

## 시스템 구성도
<img src="https://user-images.githubusercontent.com/80880587/221352810-30fa8f42-b4b7-477a-ba96-fc1e3cf9c3c9.png" width="500px" height="200px"/>

## 실제 시스템 설계
### 얼굴인식 장치
<img src="https://user-images.githubusercontent.com/80880587/221353259-ced25a8c-8455-478f-8399-44c3e94b0087.png" width="250px" height="200px"/>

### 실시간 GPS 장치
<img src="https://user-images.githubusercontent.com/80880587/221353364-cb4eb340-4aee-4d7b-9dd7-e904fd865453.png" width="350px" height="200px"/>

### 연동 어플
<div>
  <img src="https://user-images.githubusercontent.com/80880587/221353728-92bc43b4-a128-4440-9cd3-9e4dc2b87503.png" width="300px" height="500px"/>
  <img src="https://user-images.githubusercontent.com/80880587/221353779-17975021-1289-4998-85b8-75804da78849.jpg" width="300px" height="500px"/>
  <img src="https://user-images.githubusercontent.com/80880587/221353819-a54d7003-8be0-45ee-b351-81deb4b789d3.jpg" width="300px" height="500px"/>
  <img src="https://user-images.githubusercontent.com/80880587/221353841-e20ad8c0-2288-4b7c-8adc-9aa1c5b35911.jpg" width="300px" height="500px"/>
  <img src="https://user-images.githubusercontent.com/80880587/221353883-32b17762-c947-493a-a281-6b5595104b9b.jpg" width="300px" height="500px"/>
  <img src="https://user-images.githubusercontent.com/80880587/221353898-dfe62bba-a990-48c6-bb1e-7409125e8474.jpg" width="300px" height="500px"/>
</div>

## :computer: 개발환경

### :computer: 사용 언어
<div>
  <img src="https://img.shields.io/badge/Python-3776AB?style=flat&logo=Python&logoColor=white" />	
  <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white" />
  <img src="https://img.shields.io/badge/C++-00599C?style=flat&logo=C++&logoColor=white" />
</div>

### :computer: 사용 장치 및 프로그램
<div>
  <img src="https://img.shields.io/badge/Raspberry Pi-A22846?style=flat&logo=Raspberry Pi&logoColor=white" />	
	<img src="https://img.shields.io/badge/Arduino-00979D?style=flat&logo=Arduino&logoColor=white" />
  <img src="https://img.shields.io/badge/Android-3DDC84?style=flat&logo=Android&logoColor=white" />
	<img src="https://img.shields.io/badge/Android Studio-3DDC84?style=flat&logo=Android Studio&logoColor=white" />
</div>

### :computer: 서버 통신
<div>
	<img src="https://img.shields.io/badge/Firebase-FFCA28?style=flat&logo=Firebase&logoColor=white" />
	<img src="https://img.shields.io/badge/Lora-00A98F?style=flat&logo=Lora&logoColor=white" />
	<img src="https://img.shields.io/badge/Bluetooth-0082FCF?style=flat&logo=Bluetooth&logoColor=white" />
	<img src="https://img.shields.io/badge/Lora-00A98F?style=flat&logo=Lora&logoColor=white" />
</div>

## :computer: 작품 영상 (이미지 클릭)
[![Video Label](https://user-images.githubusercontent.com/80880587/221354360-d17904a1-47b7-42dc-9cb3-0a306f0fb457.png)](https://youtu.be/-bFfsAX0tb0)

