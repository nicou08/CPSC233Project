package etphoneshome.objects;

public enum SpriteURL {

    GENERIC_OBSTACLE("images/sprites/obstacles/genericObstacle.png"),
    REGULAR_PLATFORM("images/sprites/obstacles/platform/regularPlatform.png"),
    LEFT_END_PLATFORM("images/sprites/obstacles/platform/leftEndPlatform.png"),
    RIGHT_END_PLATFORM("images/sprites/obstacles/platform/rightEndPlatform.png"),
    SINGLE_PLATFORM("images/sprites/obstacles/platform/singlePlatform.png"),
    PLACEHOLDER_SPRITE("images/sprites/entities/PLACEHOLDERSPRITE.jpg"),
    ET_LEFT("images/sprites/entities/character/et_sprite_left.png"),
    ET_RIGHT("images/sprites/entities/character/et_sprite_right.png"),
    POLICE_LEFT("images/sprites/entities/enemies/police_sprite_left.png"),
    POLICE_RIGHT("images/sprites/entities/enemies/police_sprite_right.png"),
    SCIENTIST_LEFT("images/sprites/entities/enemies/scientist_sprite_left.png"),
    SCIENTIST_RIGHT("images/sprites/entities/enemies/scientist_sprite_right.png"),
    PHONE("images/sprites/phone.png"),
    PHONE_ANTENNA("images/sprites/collectibles/phone pieces/phoneAntenna.png"),
    PHONE_CHASSIS("images/sprites/collectibles/phone pieces/phoneChassis.png"),
    PHONE_KEYPAD("images/sprites/collectibles/phone pieces/phoneKeypad.png"),
    RP_BROWN("images/sprites/collectibles/reese pieces/RP_brown.png"),
    RP_ORANGE("images/sprites/collectibles/reese pieces/RP_orange.png"),
    RP_YELLOW("images/sprites/collectibles/reese pieces/RP_yellow.png"),
    HEART("images/sprites/heart.png"),
    FLASK("images/sprites/flask.png"),
    GAMEOVER("images/sprites/end game sprites/gameover.png"),
    YOU_WON("images/sprites/end game sprites/you-won.png"),
    BACKGROUND("images/backgrounds/backgroundRESIZED.jpg"),
    FINISHLINE_LEVEL_0("images/sprites/end game sprites/finishLine_level_0.png"),
    FINISHLINE_LEVEL_1(""),
    FINISHLINE_LEVEL_2(""),
    FINISHLINE_LEVEL_3("");

    private final String path;

    SpriteURL(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
