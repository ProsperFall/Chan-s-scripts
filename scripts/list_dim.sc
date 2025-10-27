
__config() -> {
   'commands' -> 
   {
      '' -> _() -> set_dim_display(player())
   }
};

__on_start() -> {
    creat_team();
    run('execute as @a run list_dim');
};

__on_close() -> {
    delete_team();
};

__on_player_connects(player) -> set_dim_display(player);
__on_player_changes_dimension(player, from_pos, from_dimension, to_pos, to_dimension) -> set_dim_display(player);

creat_team() -> {
    run('team add overworld');
    run('team modify overworld prefix {text:"[W]",color:"green"}');
    run('team add the_nether');
    run('team modify the_nether prefix {text:"[N]",color:"red"}');
    run('team add the_end');
    run('team modify the_end prefix {text:"[E]",color:"yellow"}');
    null
};

delete_team() -> {
    run('team remove overworld');
    run('team remove the_nether');
    run('team remove the_end');
    null
};


set_dim_display(sdd_tar) -> {
    run('execute as '+ sdd_tar + ' run team join ' + query(sdd_tar,'dimension'));
    null
};