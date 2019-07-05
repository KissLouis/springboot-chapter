**短信验证码实现逻辑**  
1. 用户点击发送验证码按钮
2. 随机生成一个code字符串，新建SmsCode对象，设置验证码字符串code、手机号mobile和过期时间expireTime 
3. 将SmsCode对象存入session


1. 先经过 SmsAuthenticationFilter，构造一个没有鉴权的 SmsAuthenticationToken，然后交给 AuthenticationManager 处理。
2. AuthenticationManager 通过 for-each 挑选出一个合适的 provider 进行处理，当然我们希望这个 provider 要是 SmsAuthenticationProvider。 
3. 验证通过后，重新构造一个有鉴权的 SmsAuthenticationToken，并返回给 SmsAuthenticationFilter。  
4. filter 根据上一步的验证结果，跳转到成功或者失败的处理逻辑。