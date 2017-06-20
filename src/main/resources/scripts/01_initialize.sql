CREATE TABLE users
(
  uid      BIGINT      NOT NULL,
  username VARCHAR(16) NOT NULL,
  password VARCHAR(32) NOT NULL,
  email    VARCHAR(64) NOT NULL
);

CREATE UNIQUE INDEX users_uid_uindex ON public.users (uid);
ALTER TABLE public.users
  ADD CONSTRAINT users_uid_pk PRIMARY KEY (uid);
CREATE SEQUENCE public.users_uid_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.users
  ALTER COLUMN uid SET DEFAULT nextval('public.users_uid_seq');
ALTER SEQUENCE public.users_uid_seq OWNED BY public.users.uid;