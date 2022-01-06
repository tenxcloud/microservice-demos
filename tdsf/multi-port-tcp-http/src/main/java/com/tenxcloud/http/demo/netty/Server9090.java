package com.tenxcloud.http.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author wangshixiong
 * @date 2021-07-13 下午4:39
 */
@Component
public class Server9090 implements CommandLineRunner {
    @Override
    public void run(String... args) {

        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建服务端启动器，绑定连接和对应的处理器
            ServerBootstrap bootstrap = new ServerBootstrap();
            //绑定组和添加处理
            bootstrap.group(boosGroup, workerGroup).channel(NioServerSocketChannel.class)
                    //添加自己定义的处理器
                    .childHandler(new MyHandler());
            //绑定端口连接
            ChannelFuture channelFuture = bootstrap.bind(9090).sync();
            System.out.println("Netty_Server 已启动TCP服务端口： " + 9090);
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //优雅关闭
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
