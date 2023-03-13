package com.conde.chatgptapi.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.github.flashvayne.chatgpt.dto.ChatRequest;
import io.github.flashvayne.chatgpt.dto.ChatResponse;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.extern.log4j.Log4j2;

/**
 * Simple Class @RestController.
 *
 * @author Luis Conde
 *
 */
@Log4j2
@RestController
@RequestMapping("chatgpt/api")
public class GptController implements InitializingBean {

  @Autowired
  private ChatgptService chatgptService;

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("===== Starting Chat GPT Boot ====");
  }

  @GetMapping("/chat")
  public String chatWith(@RequestParam String message) {
    log.info(message);
    return chatgptService.sendMessage(message);
  }

  @GetMapping("/prompt")
  public ChatResponse prompt(@RequestParam String message) {
    log.info(message);

    int maxTokens = 300;
    String model = "text-davinci-003";
    double temperature = 0.5;
    double topP = 1.0;

    ChatRequest chatRequest = new ChatRequest(model, message, maxTokens, temperature, topP);
    ChatResponse chatResponse = chatgptService.sendChatRequest(chatRequest);
    log.info("Response was: {0}", chatResponse);

    return chatResponse;
  }


}
