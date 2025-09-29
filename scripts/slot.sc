__config() -> {
   'commands' -> 
   {
      'helmet' -> _() -> wear_a_hat(),
      'saddle'-> _() -> wear_a_saddle(),
      'xcarry <slot>' -> 'xcarry'
   },
   'arguments' -> {
      'slot' -> { 'type' -> 'int', 'min' -> 0, 'max' -> 3, 'suggest' -> [0,1,2,3]}
   }
};

wear_a_hat() -> {
    //run('tellraw '+ player() +' [{"text":"[Warnnig]:Do not use unvanilla inventory, and close your inventory gui when you execute this command.","color":"dark_red"}]');
    run('item replace entity '+ player() +' player.cursor from entity '+ player() +' armor.head');
    run('item replace entity '+ player() +' armor.head from entity '+ player() +' weapon.mainhand');
    run('item replace entity '+ player() +' weapon.mainhand from entity '+ player() +' player.cursor');
    run('item replace entity '+ player() +' player.cursor with minecraft:air');
    null
};

wear_a_saddle() -> {
   if(
      get(query(player(), 'holds', 'mainhand'),0) == 'saddle',
      (
         run('tellraw @a [{"text":"'+ player() +'装备了一件马鞍。。。","color":"gray","italic":true}]');
         run('tellraw @a [{"text":"<'+ player() +'>我是阳光彩虹小白马！"}]');
         run('item replace entity '+ player() +' player.cursor from entity '+ player() +' saddle');
         run('item replace entity '+ player() +' saddle from entity '+ player() +' weapon.mainhand');
         run('item replace entity '+ player() +' weapon.mainhand from entity '+ player() +' player.cursor');
         run('item replace entity '+ player() +' player.cursor with minecraft:air');
         system_variable_set(player()+'isHorse',true);
      ),
      get(query(player(), 'holds', 'mainhand'),0) == null,
      (
         wasHorse = system_variable_get(player()+'isHorse');
         if(
            wasHorse == true,
            (
               run('tellraw @a [{"text":"'+ player() +'取下了马鞍。。。","color":"gray","italic":true}]');
               run('tellraw @a [{"text":"'+ player() +'不再是快乐的小马.","color":"gray","italic":true}]');
               run('item replace entity '+ player() +' player.cursor from entity '+ player() +' saddle');
               run('item replace entity '+ player() +' saddle from entity '+ player() +' weapon.mainhand');
               run('item replace entity '+ player() +' weapon.mainhand from entity '+ player() +' player.cursor');
               run('item replace entity '+ player() +' player.cursor with minecraft:air');
               system_variable_set(player()+'isHorse',false);
            )
         )
      ),
      isHorse = system_variable_get(player()+'isHorse');
      if(
         isHorse != true,
         (
            run('tellraw @a [{"text":"'+ player() +'试图假装一匹小马。。。","color":"gray","italic":true}]');
         )
      )
   );
   null
};

xcarry(slot) -> {
   run('item replace entity '+ player() +' player.cursor from entity '+ player() +' player.crafting.'+slot);//背包到缓存
   run('item replace entity '+ player() +' player.crafting.'+slot+' from entity '+ player() +' weapon.mainhand');//主手到背包
   run('item replace entity '+ player() +' weapon.mainhand from entity '+ player() +' player.cursor');//把缓存放主手
   run('item replace entity '+ player() +' player.cursor with minecraft:air');//清空缓存
   //item replace entity @s player.crafting.0 from entity @s weapon.mainhand
   null
}
