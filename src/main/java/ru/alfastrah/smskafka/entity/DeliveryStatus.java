package ru.alfastrah.smskafka.entity;

public enum DeliveryStatus {
    SAVED(64),
    PASSED_TO_MESSAGE_GATEWAY(32),
    DELIVERY_SUCCESS(1),
    DELIVERY_FAILURE(2),
    MESSAGE_BUFFERED(4),
    SMSC_SUBMIT(8),
    SMSC_REJECTED(16);

    private final int value;

    DeliveryStatus(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

    public DeliveryStatus fromId(int id){
        if(id == 1){
            return DELIVERY_SUCCESS;
        }
        else if(id == 2){
            return DELIVERY_FAILURE;
        }
        else if(id == 4){
            return MESSAGE_BUFFERED;
        }
        else if(id == 8){
            return SMSC_SUBMIT;
        }
        else if(id == 16){
            return SMSC_REJECTED;
        }
        else if( id == 32){
            return PASSED_TO_MESSAGE_GATEWAY;
        }
        else if (id == 64){
            return SAVED;
        }
        throw new IllegalArgumentException();
    }
}
