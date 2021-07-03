package com.yubin.homework.provider;


import com.yubin.homework.rpc.netty.server.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {"com.yubin.homework"})
public class ProviderApplication implements ApplicationRunner {

    private final NettyServer nettyServer;

    public ProviderApplication(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            nettyServer.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            nettyServer.destroy();
        }
    }
}
