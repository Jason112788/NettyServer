package com.jason.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Netty客户端Handler
 * @author zhuzhenhao
 * @version 1.0.0
 * @date 2019/5/1 9:53
 */
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /** id生成器 */
    private static AtomicInteger idGenerater = new AtomicInteger(0);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 注册信道
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("id:" + idGenerater.incrementAndGet(), CharsetUtil.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("客户端收到信息：" + msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }
}
