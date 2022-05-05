package domain;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Builder
@Value
public class AutoMaker {

    private final Integer id;
    private String name;

}
