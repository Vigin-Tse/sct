#1.生成秘钥命令（使用jdk工具 keytool）
keytool -genkey -alias jwt -keyalg RSA -keystore jwt.jks -keypass scarf -storepass scarf

keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey -out certificate.crt > public.crt