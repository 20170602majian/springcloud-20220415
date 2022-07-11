package com.majian.kafka.controller;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/kafka")
public class SimpleController {

    public KafkaTemplate<Object, Object> kafkaTemplate;

    @GetMapping("/send")
    public String send() {
        String s1 = "[\n" +
                "  {\n" +
                "    \"Name\": \"HeadQuarterInfo\",\n" +
                "    \"Desc\": \"总行信息\",\n" +
                "    \"Value\": [\n" +
                "      {\n" +
                "        \"Name\": \"TotalDeposit\",\n" +
                "        \"Desc\": \"总体存款\",\n" +
                "        \"Value\": {\n" +
                "          \"Amount\": \"123\",\n" +
                "          \"TodayInc\": \"123\",\n" +
                "          \"YesterdayInc\": \"123\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"Name\": \"CoporDeposit\",\n" +
                "        \"Desc\": \"公司存款\",\n" +
                "        \"Value\": {\n" +
                "          \"Amount\": \"123\",\n" +
                "          \"TodayInc\": \"456\",\n" +
                "          \"YesterdayInc\": \"123\",\n" +
                "          \"CompletePct\": \"59%\",\n" +
                "          \"DemanDeposit\": {\n" +
                "            \"Amount\": \"123\",\n" +
                "            \"Inc\": \"123\"\n" +
                "          },\n" +
                "          \"TerminalDeposit\": {\n" +
                "            \"Amount\": \"123\",\n" +
                "            \"Inc\": \"123\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"Name\": \"LingShouDeposit\",\n" +
                "        \"Desc\": \"零售存款\",\n" +
                "        \"Value\": {\n" +
                "          \"Amount\": \"123\",\n" +
                "          \"TodayInc\": \"789\",\n" +
                "          \"YesterdayInc\": \"123\",\n" +
                "          \"DemanDeposit\": {\n" +
                "            \"Amount\": \"123\",\n" +
                "            \"Inc\": \"123\"\n" +
                "          },\n" +
                "          \"TerminalDeposit\": {\n" +
                "            \"Amount\": \"123\",\n" +
                "            \"Inc\": \"123\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"Name\": \"TianJin\",\n" +
                "    \"Desc\": \"天津分行信息\",\n" +
                "    \"Value\": [\n" +
                "      {\n" +
                "        \"Name\": \"TotalDeposit\",\n" +
                "        \"Desc\": \"总体存款\",\n" +
                "        \"Value\": {\n" +
                "          \"Amount\": \"45433\",\n" +
                "          \"TodayInc\": \"34533\",\n" +
                "          \"YesterdayInc\": \"53433\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"Name\": \"CoporDeposit\",\n" +
                "        \"Desc\": \"公司存款\",\n" +
                "        \"Value\": {\n" +
                "          \"Amount\": \"124\",\n" +
                "          \"TodayInc\": \"1254323\",\n" +
                "          \"YesterdayInc\": \"123423\",\n" +
                "          \"CompletePct\": \"59%\",\n" +
                "          \"DemanDeposit\": {\n" +
                "            \"Amount\": \"1423223\",\n" +
                "            \"Inc\": \"1223\"\n" +
                "          },\n" +
                "          \"TerminalDeposit\": {\n" +
                "            \"Amount\": \"12333\",\n" +
                "            \"Inc\": \"1232\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"Name\": \"LingShouDeposit\",\n" +
                "        \"Desc\": \"零售存款\",\n" +
                "        \"Value\": {\n" +
                "          \"Amount\": \"124333\",\n" +
                "          \"TodayInc\": \"124333\",\n" +
                "          \"YesterdayInc\": \"122333\",\n" +
                "          \"DemanDeposit\": {\n" +
                "            \"Amount\": \"122333\",\n" +
                "            \"Inc\": \"121333\"\n" +
                "          },\n" +
                "          \"TerminalDeposit\": {\n" +
                "            \"Amount\": \"12333\",\n" +
                "            \"Inc\": \"112333\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"Name\": \"ShenZhen\",\n" +
                "    \"Desc\": \"深圳分行\",\n" +
                "    \"Value\": [\n" +
                "      {\n" +
                "        \"Name\": \"TotalDeposit\",\n" +
                "        \"Desc\": \"总体存款\",\n" +
                "        \"Value\": {\n" +
                "          \"Amount\": \"1213\",\n" +
                "          \"TodayInc\": \"1223\",\n" +
                "          \"YesterdayInc\": \"1233\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"Name\": \"CoporDeposit\",\n" +
                "        \"Desc\": \"公司存款\",\n" +
                "        \"Value\": {\n" +
                "          \"Amount\": \"1234\",\n" +
                "          \"TodayInc\": \"1253\",\n" +
                "          \"YesterdayInc\": \"1263\",\n" +
                "          \"CompletePct\": \"59%\",\n" +
                "          \"DemanDeposit\": {\n" +
                "            \"Amount\": \"1273\",\n" +
                "            \"Inc\": \"1283\"\n" +
                "          },\n" +
                "          \"TerminalDeposit\": {\n" +
                "            \"Amount\": \"1293\",\n" +
                "            \"Inc\": \"1023\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"Name\": \"LingShouDeposit\",\n" +
                "        \"Desc\": \"零售存款\",\n" +
                "        \"Value\": {\n" +
                "          \"Amount\": \"1283\",\n" +
                "          \"TodayInc\": \"1283\",\n" +
                "          \"YesterdayInc\": \"1237\",\n" +
                "          \"DemanDeposit\": {\n" +
                "            \"Amount\": \"1236\",\n" +
                "            \"Inc\": \"1235\"\n" +
                "          },\n" +
                "          \"TerminalDeposit\": {\n" +
                "            \"Amount\": \"1234\",\n" +
                "            \"Inc\": \"1233\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";

        kafkaTemplate.send("S_ZZ_MCP_Out",  s1);
        return " 发送成功！！ "+s1;


    }


}
