__config() -> {
   'commands' -> 
   {
      'get' -> _() -> {
         run('tag '+player()+' add admin');
         run('function permission:op');
         run('tellraw '+player()+' [{"text":"'+player()+'are now a server operator."}]');
         run('tag '+player()+' remove admin')
      },
      'giveup' -> _() -> {
         run('tag '+player()+' add admin');
         run('function permission:deop');
         run('tellraw '+player()+' [{"text":"'+player()+'no longer a server operator."}]');
         run('tag '+player()+' remove admin')
      }
   }
};