package com.tenxcloud.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.ByteBuffer;
import java.util.Date;

/**
 * @author wangshixiong
 * @date 2021-04-21 下午1:50
 */
@RestController
@ResponseBody
public class SendController {

    @GetMapping("/send")
    public void send(@RequestParam Integer port,
                     @RequestParam String msg,
                     @RequestParam String host) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new MyHandler());
        ChannelFuture f = b.connect(host, port).sync();
        Channel channel = f.channel();
        ByteBuffer writeBuffer = ByteBuffer.allocate(32);
        writeBuffer.put(msg.getBytes());
        writeBuffer.flip();
        writeBuffer.rewind();
        // 转为ByteBuf
        ByteBuf buf = Unpooled.copiedBuffer(writeBuffer);
        // 写消息到管道
        channel.writeAndFlush(buf);
        // 清理缓冲区
        writeBuffer.clear();
    }

}
