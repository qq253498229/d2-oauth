insert ignore into `t_client`
(`id`, `client_id`, `client_secret`,
 `authorized_grant_types`, `scope`,
 `refresh_token_validity_seconds`, `access_token_validity_seconds`,
 `registered_redirect_uri`)
values (1, 'client', '$2a$10$YACEb/LqIN2eZRSd2sKbuOleAsCm7PIJQ0ob73FKxGrD3dmeGUZPy',
        'password,authorization_code,refresh_token', 'app',
        604800, 7200, null);

insert into t_role (name)
values ('USER');