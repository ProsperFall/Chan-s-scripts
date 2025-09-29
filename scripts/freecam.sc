__config() -> {
   'commands' -> 
   {
      '' -> _() -> switch_mode(),
      'active' -> _() -> {
         iscam = system_variable_get(player() + 'isCam');
         if(
            iscam != true,
            (
               active_freecam_mode()
            ),
            run('tellraw '+player()+' [{"text":"您已处于相机模式"}]');
         );
      },
      'deactive'-> _() -> {
         iscam = system_variable_get(player() + 'isCam');
         if(
            iscam == true,
            (
               deactive_freecam_mode()
            )
         );
      },
      'tp <location>' -> ['cam_teleport',query(player(), 'dimension'),query(player(), 'location')],
      'tp <location> <dimension>' -> ['cam_teleport',query(player(), 'location')],
      'tp <location> <dimension> <rotation>' -> ['cam_teleport'],
   },
   'arguments' -> {
      'rotation' -> { 'type' -> 'rotation','suggest' -> ['0 90','0 0']}
   }
};

cam_teleport(position,dimension,rotation) -> {
   iscam = system_variable_get(player() + 'isCam');
   if(
      iscam == true,
      (
         teleport(get(position,0),get(position,1),get(position,2),dimension,get(rotation,3),get(rotation,4));
      ),
      run('tellraw '+player()+' [{"text":"不允许非相机模式的玩家:['+player()+']使用传送."}]');
   );
   null
};

teleport(x,y,z,dimension,yaw,pitch) -> {
   run('execute in minecraft:'+dimension+' run tp '+ player() +' '+x+' '+y+' '+z+' '+yaw+' '+pitch);
   null
};

switch_mode() -> {
   iscam = system_variable_get(player() + 'isCam');
   if(
      iscam == true,
      (
         deactive_freecam_mode()
      ),
      iscam == false,
      (
         active_freecam_mode()
      ),
      active_freecam_mode()
   );
   null
};

creat_data() -> {
   data = [
      query(player(), 'dimension'),
      query(player(), 'location'),
      query(player(), 'effect')
   ];
   system_variable_set(player() + 'data', data);
   system_variable_set(player() + 'gm', query(player(), 'gamemode'));
   null
};

set_effect() -> {
   effects = get(system_variable_get(player() + 'data'),2);
   //print(effects);  没看懂for循环和goto，break怎么写这样弄了
   for (
      range(128), 
      (
         if(
            get(get(effects,_),0) != null,
            (
               modify(player(), 'effect', get(get(effects,_),0), get(get(effects,_),2), get(get(effects,0),1))
            ),
            return(null)
         );
      );
   );
};

active_freecam_mode() -> {
   if(
      query(player(),'gamemode') == 'spectator',
      (
         run('tellraw '+player()+' [{"text":"访客不允许模式切换，请咨询服务器管理员."}]');
      ),
      creat_data();
      modify(player(),'gamemode','spectator');
      modify(player(), 'effect');
      modify(player(), 'effect', 'night_vision', -1, 0, false, false);
      system_variable_set(player() + 'isCam',true);
   );
   null
};

deactive_freecam_mode() -> {
   data = system_variable_get(player() + 'data');
   gamemode = system_variable_get(player() + 'gm');
   if(
      gamemode == 'spectator',
      (
         run('tellraw '+player()+' [{"text":"访客不允许模式切换，请咨询服务器管理员."}]');
      ),
      modify(player(), 'effect', 'night_vision', 0);
      set_effect();
      teleport(get(get(data,1),0),get(get(data,1),1),get(get(data,1),2),get(data,0),get(get(data,1),3),get(get(data,1),4));
      modify(player(),'gamemode',gamemode);
      system_variable_set(player() + 'isCam',false);
   );
   null
};
