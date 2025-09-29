__config() -> {
   'commands' -> 
   {
      'here' -> _() -> say_Im_here(),
      '<location>' -> ['say_where',query(player(), 'dimension')],
      '<location> <dimension>' -> 'say_where',
   }
};

say_Im_here() -> {
   run('tellraw @a [{"text":"<'+player()+'>","color":"gold"},{"text":"I\'m here! ","bold":true,"color":"white"},{"text":"[In:'+query(player(), 'dimension')+'] ","color":"red"},{"color":"green","click_event":{"action":"run_command","command":"execute in minecraft:'+query(player(), 'dimension')+' run tp @s '+str('%.2f %.2f %.2f',pos(player()))+'"},"text":"At ['+str('x:%d,y:%d,z:%d',pos(player()))+']"}]');
   run('tellraw @a [{"text":"Click on pathfinding.","underlined":true,"click_event":{"action":"suggest_command","command":"#goto '+str('%d %d %d',pos(player()))+'"},"hover_event":{"action":"show_text","value":"Only works on baritone."}}]');
   null
};

say_where(pos,dim) -> {
   run('tellraw @a [{"text":"<'+player()+'>","color":"gold"},{"text":"Here! ","bold":true,"color":"white"},{"text":"[In:'+dim+'] ","color":"red"},{"color":"green","click_event":{"action":"run_command","command":"execute in minecraft:'+dim+' run tp @s '+str('%.2f %.2f %.2f',pos)+'"},"text":"At ['+str('x:%d,y:%d,z:%d',pos)+']"}]');
   run('tellraw @a [{"text":"Click on pathfinding.","underlined":true,"click_event":{"action":"suggest_command","command":"#goto '+str('%d %d %d',pos)+'"},"hover_event":{"action":"show_text","value":"Only works on baritone."}}]');
   null
};