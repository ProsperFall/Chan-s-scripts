__on_player_respawns(player)  -> play_see_you_again(player);
__on_player_dies(player) -> play_see_you_again_ends(player);

__config() -> {
   'commands' -> 
   {
      '' -> _() -> {
        run('tellraw @a [{"text":"<'+ player() +'> What can I say."}]');
        play_see_you_again(player())
      },
   }
};

events_run(events_str) -> run(events_str);

play_see_you_again(Ns_events_player) -> {
    schedule(4, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 0.943874');
    schedule(8, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 1.414214');
    schedule(12, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 1.259921');
    schedule(16, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 0.943874');
    
    schedule(24, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 1.259921');
    schedule(26, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 1.414214');
    schedule(28, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 1.587401');
    schedule(30, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 1.259921');
    schedule(32, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 1.259921');
    schedule(34, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 1.414214');
 
    schedule(36, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 0.943874');
    schedule(40, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 1.414214');
    schedule(44, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 1.259921'); 
    schedule(48, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 0.943874');
};
play_see_you_again_ends(Ns_events_player) -> {
    schedule(4, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 1 1.259921');
    schedule(8, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 0.9 1.259921');
    schedule(13, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 0.8 1.122462'); 
    schedule(17, 'events_run', 'execute as '+ Ns_events_player +' at @s run playsound minecraft:block.note_block.bell voice @a ~ ~ ~ 0.7 1.122462');
}