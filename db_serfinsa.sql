/*
 Navicat Premium Data Transfer

 Source Server         : PostgreSQL
 Source Server Type    : PostgreSQL
 Source Server Version : 140008 (140008)
 Source Host           : 127.0.0.1:5432
 Source Catalog        : db_serfinsa
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140008 (140008)
 File Encoding         : 65001

 Date: 30/11/2023 21:28:13
*/


-- ----------------------------
-- Table structure for tb_cliente
-- ----------------------------
DROP TABLE IF EXISTS "public"."tb_cliente";
CREATE TABLE "public"."tb_cliente" (
  "id_cliente" uuid NOT NULL,
  "nombre" varchar(75) COLLATE "pg_catalog"."default",
  "telefono" varchar(9) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."tb_cliente" OWNER TO "josue";

-- ----------------------------
-- Records of tb_cliente
-- ----------------------------
BEGIN;
INSERT INTO "public"."tb_cliente" ("id_cliente", "nombre", "telefono") VALUES ('e94c654b-f403-442e-9c9b-167671492f7f', 'Pablo Jose Antonio SA de CV', '75434126');
INSERT INTO "public"."tb_cliente" ("id_cliente", "nombre", "telefono") VALUES ('7480f5ed-c1a8-4590-a480-e090eb740804', 'Edwin Antonio', '75434127');
INSERT INTO "public"."tb_cliente" ("id_cliente", "nombre", "telefono") VALUES ('5983fdb3-3fbe-44c7-ad83-5205dbfce487', 'Josue Carmona', '75412658');
COMMIT;

-- ----------------------------
-- Table structure for tb_comercio
-- ----------------------------
DROP TABLE IF EXISTS "public"."tb_comercio";
CREATE TABLE "public"."tb_comercio" (
  "id_comercio" uuid NOT NULL,
  "nombre" varchar(75) COLLATE "pg_catalog"."default",
  "ubicacion" varchar(255) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."tb_comercio" OWNER TO "josue";

-- ----------------------------
-- Records of tb_comercio
-- ----------------------------
BEGIN;
INSERT INTO "public"."tb_comercio" ("id_comercio", "nombre", "ubicacion") VALUES ('a9b19864-c63f-4202-8365-87d672ad734e', 'Pablo Jose Antonio SA de CV', 'la colonia');
INSERT INTO "public"."tb_comercio" ("id_comercio", "nombre", "ubicacion") VALUES ('9b85e4e8-d8c3-4191-84a9-a9a1b31220d7', 'Brayan SA de CV', 'la esquina');
COMMIT;

-- ----------------------------
-- Table structure for tb_compra
-- ----------------------------
DROP TABLE IF EXISTS "public"."tb_compra";
CREATE TABLE "public"."tb_compra" (
  "id_compra" uuid NOT NULL,
  "fecha_compra" timestamp(6),
  "monto_total" float8,
  "tipo_pago" varchar(50) COLLATE "pg_catalog"."default",
  "id_cliente" uuid NOT NULL,
  "id_comercio" uuid NOT NULL
)
;
ALTER TABLE "public"."tb_compra" OWNER TO "josue";

-- ----------------------------
-- Records of tb_compra
-- ----------------------------
BEGIN;
INSERT INTO "public"."tb_compra" ("id_compra", "fecha_compra", "monto_total", "tipo_pago", "id_cliente", "id_comercio") VALUES ('868df121-730f-4ce8-ba77-8aabf2a38e1e', '2023-08-30 20:41:00', 100, 'Plazos', 'e94c654b-f403-442e-9c9b-167671492f7f', 'a9b19864-c63f-4202-8365-87d672ad734e');
INSERT INTO "public"."tb_compra" ("id_compra", "fecha_compra", "monto_total", "tipo_pago", "id_cliente", "id_comercio") VALUES ('5f8defc7-351d-44b1-8436-1ce2a0f337ef', '2023-08-30 20:41:00', 565, 'Tarjeta', '7480f5ed-c1a8-4590-a480-e090eb740804', 'a9b19864-c63f-4202-8365-87d672ad734e');
INSERT INTO "public"."tb_compra" ("id_compra", "fecha_compra", "monto_total", "tipo_pago", "id_cliente", "id_comercio") VALUES ('964c3d85-a38f-4cd9-a67d-f98773ee368b', '2023-11-30 20:41:00', 235, 'Tarjeta', '7480f5ed-c1a8-4590-a480-e090eb740804', 'a9b19864-c63f-4202-8365-87d672ad734e');
INSERT INTO "public"."tb_compra" ("id_compra", "fecha_compra", "monto_total", "tipo_pago", "id_cliente", "id_comercio") VALUES ('a80a4a1d-08c7-48e6-9fdf-bc945bf458b8', '2023-11-30 20:41:00', 899, 'Tarjeta', '5983fdb3-3fbe-44c7-ad83-5205dbfce487', 'a9b19864-c63f-4202-8365-87d672ad734e');
INSERT INTO "public"."tb_compra" ("id_compra", "fecha_compra", "monto_total", "tipo_pago", "id_cliente", "id_comercio") VALUES ('a467de4a-ab84-4a62-8085-1a72a818ee50', '2023-11-29 20:41:00', 50, 'Plazos', '5983fdb3-3fbe-44c7-ad83-5205dbfce487', '9b85e4e8-d8c3-4191-84a9-a9a1b31220d7');
COMMIT;

-- ----------------------------
-- Uniques structure for table tb_cliente
-- ----------------------------
ALTER TABLE "public"."tb_cliente" ADD CONSTRAINT "uk_sa8i3j4esceawq01va7dy4kf8" UNIQUE ("nombre");
ALTER TABLE "public"."tb_cliente" ADD CONSTRAINT "uk_8soktpe0hbx4gpskg8p0lhcg7" UNIQUE ("telefono");

-- ----------------------------
-- Primary Key structure for table tb_cliente
-- ----------------------------
ALTER TABLE "public"."tb_cliente" ADD CONSTRAINT "tb_cliente_pkey" PRIMARY KEY ("id_cliente");

-- ----------------------------
-- Uniques structure for table tb_comercio
-- ----------------------------
ALTER TABLE "public"."tb_comercio" ADD CONSTRAINT "uk_fa6ooacp5carqhcgt27u6kk1b" UNIQUE ("nombre");

-- ----------------------------
-- Primary Key structure for table tb_comercio
-- ----------------------------
ALTER TABLE "public"."tb_comercio" ADD CONSTRAINT "tb_comercio_pkey" PRIMARY KEY ("id_comercio");

-- ----------------------------
-- Primary Key structure for table tb_compra
-- ----------------------------
ALTER TABLE "public"."tb_compra" ADD CONSTRAINT "tb_compra_pkey" PRIMARY KEY ("id_compra");

-- ----------------------------
-- Foreign Keys structure for table tb_compra
-- ----------------------------
ALTER TABLE "public"."tb_compra" ADD CONSTRAINT "FK-cliente" FOREIGN KEY ("id_cliente") REFERENCES "public"."tb_cliente" ("id_cliente") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."tb_compra" ADD CONSTRAINT "FK-comercio" FOREIGN KEY ("id_comercio") REFERENCES "public"."tb_comercio" ("id_comercio") ON DELETE NO ACTION ON UPDATE NO ACTION;
