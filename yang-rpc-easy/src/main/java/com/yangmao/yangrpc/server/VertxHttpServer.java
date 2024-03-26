package com.yangmao.yangrpc.server;

import com.yangmao.yangrpc.server.HttpServer;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daichenyang <daichenyang@kuaishou.com>
 * Created on 2024-03-25
 */
@Slf4j
public class VertxHttpServer implements HttpServer {
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();
//        server.requestHandler(new Handler<HttpServerRequest>() {
//                                  @Override
//                                  public void handle(HttpServerRequest req) {
//                                      log.info("receive request: " + req.method() + " " + req.uri());
//                                      req.response()
//                                              .putHeader("content-type", "text/plain")
//                                              .end("Hello from Vert.x-Web!");
//                                  }
//                              }
//        );
        server.requestHandler(new HttpServerHandler());
        server.listen(port, res -> {
            if (res.succeeded()) {
                log.info("HTTP server started on port " + port);
            } else {
                log.info("HTTP server failed to start: " + res.cause());
            }
        });


    }
}
