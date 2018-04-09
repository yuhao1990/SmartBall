package com.lovnx.websocket;

import com.lovnx.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
@ServerEndpoint(value = "/websocket", configurator = CustomSpringConfigurator.class)
@Scope(value = "prototype")
public class MyWebSocket {

    private HttpSession httpSession;

    private static HashMap<HttpSession, HttpSession> httpSessionMap = new HashMap<HttpSession, HttpSession>();

    public static HashMap<HttpSession, HttpSession> getHttpSessionMap() {
        return httpSessionMap;
    }

    public static void setHttpSessionMap(HashMap<HttpSession, HttpSession> httpSessionMap) {
        MyWebSocket.httpSessionMap = httpSessionMap;
    }

    /*@Autowired
    private ScoreService ss;*/

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        try {
            HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
            this.httpSession = httpSession;
            if (httpSessionMap.containsKey(httpSession)) {
                ((Session) this.httpSession.getAttribute("wsSession")).close();
            }
            this.httpSession.setAttribute("wsSession", session);
            httpSessionMap.put(this.httpSession, null);
            Iterator iter = httpSessionMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                HttpSession key = (HttpSession)entry.getKey();
                HttpSession val = (HttpSession)entry.getValue();
                if(key!=this.httpSession && val == null){
                    System.out.println("成功匹配-主*"+key.getId().substring(10)+"-从*"+this.httpSession.getId().substring(10));
                    httpSessionMap.put(this.httpSession, key);
                    entry.setValue(this.httpSession);
                    Session s2 = (Session) key.getAttribute("wsSession");
                    this.sendMessage(s2, "1#matched");
                    this.sendMessage(session, "1#matched");
                    break;
                }
            }
            System.out.println("有新连接加入！当前在线人数为" + httpSessionMap.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        //this.httpSession.removeAttribute("wsSession");
        httpSessionMap.remove(this.httpSession);
        Iterator iter = httpSessionMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            HttpSession key = (HttpSession)entry.getKey();
            HttpSession val = (HttpSession)entry.getValue();
            if(val == this.httpSession){
                System.out.println("配对断开-*"+this.httpSession.getId().substring(10)+"离开-*"+key.getId().substring(10)+"留下");
                entry.setValue(null);
                Session s = (Session) key.getAttribute("wsSession");
                this.sendMessage(s, "2#disconnect");
                break;
            }
        }
        System.out.println("有一连接关闭！当前在线人数为" + httpSessionMap.size());
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("来自客户端的消息:" + message);
        Iterator iter = httpSessionMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            HttpSession key = (HttpSession)entry.getKey();
            HttpSession val = (HttpSession)entry.getValue();
            if(val == this.httpSession){
                Session s = (Session) key.getAttribute("wsSession");
                this.sendMessage(s, "score#"+message);
                break;
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(Session session, String message)  {
        synchronized (session){
            try {
                session.getBasicRemote().sendText(message);
                //session.getAsyncRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendInfo(String message) throws IOException {
        Iterator iter = httpSessionMap.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            HttpSession val = (HttpSession) httpSessionMap.get(key);
            sendMessage((Session) ((val).getAttribute("wsSession")), message);
        }
    }

}
