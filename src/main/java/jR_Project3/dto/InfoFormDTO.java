package jR_Project3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class InfoFormDTO implements Serializable {
    private String userName;
    private String userIP;
    private String JSessionID;
    private Integer stepsInGame;
    private Integer countPlayedGame;

    private InfoFormDTO(InfoFormDTOBuilder builder) {
        this.userName = builder.userName;
        this.userIP = builder.userIP;
        this.JSessionID = builder.JSessionID;
        this.stepsInGame = builder.stepsInGame;
        this.countPlayedGame = builder.countPlayedGame;
    }

    public static class InfoFormDTOBuilder {
        private String userName;
        private String userIP;
        private String JSessionID;
        private Integer stepsInGame;
        private Integer countPlayedGame;

        public InfoFormDTOBuilder setUserName(String name) {
            this.userName = name;
            return this;
        }

        public InfoFormDTOBuilder setUserIP(String ip) {
            this.userIP = ip;
            return this;
        }

        public InfoFormDTOBuilder setJSessionID(String JSessionID) {
            this.JSessionID = JSessionID;
            return this;
        }

        public InfoFormDTOBuilder setStepsInGame(Integer stepsInGame) {
            this.stepsInGame = stepsInGame;
            return this;
        }

        public InfoFormDTOBuilder setCountPlayedGame(Integer countPlayedGame) {
            this.countPlayedGame = countPlayedGame;
            return this;
        }

        public InfoFormDTO build() {
            return new InfoFormDTO(this);
        }

    }
}
