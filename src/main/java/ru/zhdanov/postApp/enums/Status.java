package ru.zhdanov.postApp.enums;

import lombok.Getter;

@Getter
public enum Status {
            READY_TO_SEND("готово к отправке"),
           TRANSIT("в пути") ,
            RECEIVED("получено");

           private String name;

           Status(String name){
               this.name=name;
           }


}
