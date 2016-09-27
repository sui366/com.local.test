package com.test.socket;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaShortConnServerHandler extends IoHandlerAdapter {

    private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

 

    @Override

    public void sessionOpened(IoSession session) { 

       InetSocketAddress remoteAddress = (InetSocketAddress)session.getRemoteAddress();

       logger.info(remoteAddress.getAddress().getHostAddress());

       logger.info(String.valueOf(session.getId()));

    }

 

    @Override

    public void messageReceived(IoSession session, Object message) {

       logger.info("Messagereceived in the short connect server...");

       String expression = message.toString();

       Initialization init = Initialization.getInstance();

       HashMap<String, IoSession> clientMap =init.getClientMap();

       if (clientMap == null || clientMap.size() == 0) {

           session.write("error");

       } else {

           IoSession longConnSession = null;

           Iterator<String> iterator =clientMap.keySet().iterator();

           String key = "";

           while (iterator.hasNext()) {

              key = iterator.next();

              longConnSession = clientMap.get(key);

           }

           logger.info("ShortConnect Server Session ID :"+String.valueOf(session.getId()));

           logger.info("LongConnect Server Session ID :"+String.valueOf(longConnSession.getId()));

           longConnSession.setAttribute("shortConnSession",session);

           longConnSession.write(expression);

       }

    }

 

    @Override

    public void sessionIdle(IoSession session, IdleStatus status) {

       logger.info("Disconnectingthe idle.");

       // disconnect an idle client

       session.close(true);

    }

 

    @Override

    public void exceptionCaught(IoSession session, Throwable cause) {

       // close the connection onexceptional situation

       logger.warn(cause.getMessage(), cause);

       session.close(true);

    }

}