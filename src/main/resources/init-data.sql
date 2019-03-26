insert ignore into `t_client`
(`id`, `client_id`, `client_secret`,
 `authorized_grant_types`, `scope`,
 `refresh_token_validity_seconds`, `access_token_validity_seconds`,
 `registered_redirect_uri`)
values (1, 'client', '$2a$10$YACEb/LqIN2eZRSd2sKbuOleAsCm7PIJQ0ob73FKxGrD3dmeGUZPy',
        'password,authorization_code,refresh_token', 'app',
        604800, 7200,
        'http://localhost:4200,https://www.baidu.com,https://test.codeforexp.cn');

insert ignore into `t_user`
(`id`, `username`, `password`, `enabled`, `update_at`, `create_at`, `sex`, `phone_id`, `user_id`)
values (1, 'user', '$2a$10$aAZeACgARa/UHeNvPAhhZe3hooPCAadIL/2/ATuJodtb3luiCe8Au',
        true, now(), now(), null, null, null);

insert ignore into `t_role` (`id`, `name`)
values (1, 'USER');

insert ignore into `t_user_role` (`user_id`, `role_id`)
values (1, 1);