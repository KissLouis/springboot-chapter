
###Spring Boot配置HTTPS,并实现HTTP访问自动转HTTPS访问

#### 1.使用 keytools 自签名生成创建证书
    * keytool -genkey -alias tomcathttps -keyalg RSA -keysize 2048 -keystore server.keystore -validity 365

#### 2.SpringBoot配置SSL

#### 3.http访问自动转https访问  