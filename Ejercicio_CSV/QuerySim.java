/*private void asignRolesToUser() {
        List<User> userList = this.cargadorDeDatos.users.getAllUsers();
        String[] userNames = userList.stream().map(User::getName).toArray(String[]::new);
        int resultUser = showMenu("Seleccione un usuario al cual asignarle roles: ", userNames);
        String userId = userList.get(resultUser-1).getId();

        List<Role> roleList = this.cargadorDeDatos.roles.getAllRoles();
        String[] roles = roleList.stream().map(Role::getName).toArray(String[]::new);
        List<Integer> resultRoles = showMenuList("Seleccione los roles que desea asignar al usuario: ", roles);

        List<Role> selectedRoles = new ArrayList<>();

        for (Integer roleIndex : resultRoles) {
            Role selectedRole = roleList.get(roleIndex-1);
            selectedRoles.add(selectedRole);
        }

        this.cargadorDeDatos.users.asignRoles(userId, selectedRoles);

        int viewPermissions = showMenu("Desea ver los permisos que tiene el usuario?", new String[]{"Si", "No"});
        if (viewPermissions == 1) {
            this.cargadorDeDatos.users.returnUserPermissions(String.valueOf(resultUser));
        }
    }

}*/