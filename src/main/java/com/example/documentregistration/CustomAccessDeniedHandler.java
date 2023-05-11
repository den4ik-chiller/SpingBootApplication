package com.example.documentregistration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * CustomAccessDeniedHandler - класс, реализующий интерфейс AccessDeniedHandler для обработки исключений доступа к защищенным ресурсам приложения.
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * Метод handle обрабатывает исключения AccessDeniedException и отправляет пользователю страницу с ошибкой 403, если доступ запрещен.
     * @param request объект HttpServletRequest, представляющий HTTP-запрос, который привел к возникновению исключения AccessDeniedException
     * @param response объект HttpServletResponse, представляющий HTTP-ответ, который будет отправлен пользователю
     * @param exception объект AccessDeniedException, представляющий исключение, которое было выброшено при доступе к защищенному ресурсу
     * @throws IOException если возникают ошибки при чтении или записи в поток данных
     * @throws ServletException если сервлет не может обработать запрос
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        if (!response.isCommitted()) {
            response.setContentType("text/html;charset=UTF-8");

            // read the contents of the HTML file
            InputStream inputStream = new FileInputStream("src/main/resources/templates/error/403.html");
            String htmlContent = IOUtils.toString(inputStream, "UTF-8");
            inputStream.close();

            // write the HTML content to the response
            response.getWriter().write(htmlContent);
        }
    }
}
