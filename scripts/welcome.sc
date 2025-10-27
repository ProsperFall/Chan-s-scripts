__on_player_connects(player) -> {
    welcome(player);
};
__on_player_disconnects(player, reason) -> {
    goodbye(player,reason);
};

welcome(player) -> {
    run('tellraw @a [{text:"'+player+'",color:gold},{text:", Ciallo～(∠・ω )⌒☆",color:white}]');
    hour = get(convert_date(unix_time()),3);
    if(
        hour >= 6 && hour <= 10,
        (
            run('tellraw '+ player +' [{text:"早上好喵~"}]');
        ),
        hour >= 11 && hour <= 14,
        (
            run('tellraw '+ player +' [{text:"中午好喵~"}]');
        ),
        hour >= 15 && hour <= 18,
        (
            run('tellraw '+ player +' [{text:"下午好喵~"}]');
        ),
        hour >= 19 && hour <= 23,
        (
            run('tellraw '+ player +' [{text:"晚上好喵~"}]');
        ),
        hour >= 24 || hour <= 5,
        (
            run('tellraw '+ player +' [{text:"注意休息呀~喵~"}]');
        )
    );
    null
};

goodbye(player,reason) -> {
    run('tellraw @a {}');
    run('tell @a Goodbye' + reason);
    null
};