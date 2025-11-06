//package com.mackenzie.e_commerce.config;
//
//import com.mercadopago.MercadoPagoConfig;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//
//@Configuration
//public class ConfigMercadoPago {
//
//    @Value("${mercadopago.access-token}")
//    private String accessToken;
//
//    @PostConstruct
//    public void init() {
//        if (accessToken != null || accessToken.isEmpty()) {
//            System.err.println("!!! ATENÇÃO: MERCADOPAGO_ACCESS_TOKEN não está configurado!");
//            System.err.println("!!! O pagamento PIX não funcionará.");
//            return;
//        }
//
//        System.out.println("Configurando SDK do Mercado Pago...");
//        MercadoPagoConfig.setAccessToken(accessToken);
//        System.out.println("SDK do Mercado Pago configurado com sucesso.");
//    }
//}
