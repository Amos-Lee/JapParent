# JapParent

获取授权码：http://localhost:8080/oauth/authorize?response_type=code&client_id=client&redirect_uri=http://www.baidu.com

获取访问令牌：http://localhost:8080/oauth/token

Postman:

  Authorization: Basic Auth 
  
    Username:client
    Password:123456
    
  Body: x-www-form-urlencoded
  
    grant_type:authorization_code
    code:用授权码获取
    client_id：client
    redirect_uri：http://www.baidu.com
    scopes：all
    
Swagger:
    http://localhost:8080/swagger-ui.html

