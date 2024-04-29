# 기본 이미지로 nginx:latest 이미지를 사용
FROM nginx:latest

# dist 폴더 내 파일들을 컨테이너 내의 nginx 서버에 복사
 ADD ./dist/css /usr/share/nginx/html/css
 ADD ./dist/fonts /usr/share/nginx/html/fonts
 ADD ./dist/img /usr/share/nginx/html/img
 ADD ./dist/js /usr/share/nginx/html/js
 ADD ./dist/styles.css /usr/share/nginx/html/styles.css
 ADD ./dist/logo.png /usr/share/nginx/html/logo.png

 # 기존에 생성되어 있는 index.html 파일을 삭제
 RUN rm -rf /usr/share/nginx/html/index.html

 # 새로운 index.html 파일을 추가
 ADD ./dist/index.html /usr/share/nginx/html/index.html

 # 기존에 생성되어 있는 nginx 설정파일을 삭제
 RUN rm -rf /etc/nginx/conf.d/default.conf

 # 새로운 default.conf 파일을 추가
 ADD ./nginx/default.conf /etc/nginx/conf.d/default.conf

 # 컨테이너가 시작될 때 nginx를 실행시키고, 데몬 모드(백그라운드 실행)로 동작토록 설정
 CMD ["nginx", "-g", "daemon off;"]