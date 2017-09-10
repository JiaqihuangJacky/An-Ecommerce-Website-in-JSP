DEMO:
http://www.voidcn.com/blog/change_on/article/p-6660269.html

Explanation:
http://blog.csdn.net/change_on/article/details/73881791

springboot环境 install

https://www.youtube.com/watch?v=nOCbLRUdePs&t=38s

pom引进paypal-sdk的jar包

码代码

测试

后言

In src/main/resources:application.properties
server.port: 8088
spring.thymeleaf.cache=false

paypal.mode=sandbox
paypal.client.app=AaY8eAJpGTMUnGXzno1JBrsCCToU2qWQSTfKNLklVEUVQK33bTjYo1YMEh-xU_9lfcD3UMp74o7TVECC
paypal.client.secret=EKnlRGdjoFETMV9Aim3h7JwJLD8wVijZt3RA8JYJwZsqgR0tr9bGhJSY2dYZ6XBDjq9Iwbv4uYWbSvqn

This is my developer account




In payment controller, the following will change the amount you send
 try {
            Payment payment = paypalService.createPayment(
                    1.00, 
                    "USD", 
                    PaypalPaymentMethod.paypal, 
                    PaypalPaymentIntent.sale,
                    "payment description", 
                    cancelUrl, 
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }