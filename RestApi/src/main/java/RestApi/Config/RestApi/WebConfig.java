//package RestApi.Config.RestApi;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class WebConfig {
//    @Value("${url.base:''}")
//    private String urlBase;
//
//    @Value("${cors.allowed.origins:[]}")
//    private String[] allowedOrigins;
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer(){
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry){
//                registry.addMapping(urlBase + "/**")
//                        .allowedOrigins(allowedOrigins)
//                        .allowedMethods("*");
//            }
//        };
//    }
//
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**").allowedOrigins("*");;
////    }
//
//}
