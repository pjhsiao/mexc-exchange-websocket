package invest.robot.mexc.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Channel SUBSCRIPTION Entity
 * ex: "spot@public.deals.v3.api@BTCUSDT";
 */
@Getter
@Setter
@Builder
public class ChannelDataEntity {
    private String channelName;
    private String reqStreamMethod;
    private String reqStreamParams;

    protected String getJsonTempleted(){
        String jsonTempleted = "{\n" +
                    "    \"method\": \"%s\",\n" +
                    "    \"params\": [\n" +
                    "        \"%s\"\n" +
                    "    ]\n" +
                    " } ";

        return jsonTempleted;
    }
    public String getRequestJson(){
        return String.format(getJsonTempleted(),  reqStreamMethod, reqStreamParams);
    }


}
