CREATE TABLE category (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255),
                          alias VARCHAR(50) DEFAULT NULL,
                          type VARCHAR(30) NOT NULL,
                          parent_id INTEGER,
                          ordering INTEGER,
                          description TEXT,
                          text TEXT,
                          status VARCHAR(30),
                          created TIMESTAMP,
                          createdby INTEGER,
                          createdbyalias VARCHAR(60),
                          updated TIMESTAMP,
                          updatedby INTEGER,
                          updatedbyalias VARCHAR(60),
                          siteid INTEGER NOT NULL,
                          lang CHAR(2) NOT NULL,
                          photo VARCHAR(255) NULL,
                          CONSTRAINT category_alias_siteid_unique UNIQUE (alias, siteid),
                          CONSTRAINT category_parent_id_fk FOREIGN KEY (parent_id) REFERENCES category(id) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT category_siteid_lang_fk FOREIGN KEY (siteid) REFERENCES website(id)
);

-- Creating indexes
CREATE INDEX category_ordering_idx ON category(ordering);
CREATE INDEX category_type_idx ON category(type);
CREATE INDEX category_status_idx ON category(status);


CREATE TABLE photo (
                       id SERIAL PRIMARY KEY,                    -- Use SERIAL for auto-increment
                       category_id INTEGER REFERENCES category(id) ON DELETE SET NULL ON UPDATE CASCADE,  -- Foreign key with proper action
                       name VARCHAR(255),
                       width INTEGER,
                       height INTEGER,
                       path VARCHAR(255),
                       size INTEGER,
                       status VARCHAR(30),
                       created TIMESTAMPTZ,                      -- Use TIMESTAMPTZ for timestamp with timezone
                       createdby INTEGER,
                       createdbyalias VARCHAR(60),
                       updated TIMESTAMPTZ,
                       updatedby INTEGER,
                       updatedbyalias VARCHAR(60),
                       siteid INTEGER NOT NULL,
                       CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE SET NULL ON UPDATE CASCADE  -- Foreign Key
);

CREATE TABLE video (
                       id SERIAL PRIMARY KEY,                     -- Use SERIAL for auto-increment
                       category_id INTEGER REFERENCES category(id) ON DELETE SET NULL ON UPDATE CASCADE,  -- Foreign key with proper action
                       name VARCHAR(100),
                       photo VARCHAR(255),
                       description TEXT,
                       path VARCHAR(255),
                       type VARCHAR(50),
                       size INTEGER,
                       created TIMESTAMPTZ,                       -- Use TIMESTAMPTZ for timestamp with timezone
                       createdby INTEGER,
                       createdbyalias VARCHAR(60),
                       status VARCHAR(30),
                       siteid INTEGER NOT NULL
);


CREATE TABLE cms.category (
                              id serial4 NOT NULL,
                              "name" varchar(255) NULL,
                              alias varchar(50) DEFAULT NULL::character varying NULL,
                              "type" varchar(30) NOT NULL,
                              parent_id int4 NULL,
                              "ordering" int4 NULL,
                              description text NULL,
                              "text" text NULL,
                              status varchar(30) NULL,
                              created timestamp NULL,
                              createdby int4 NULL,
                              createdbyalias varchar(60) NULL,
                              updated timestamp NULL,
                              updatedby int4 NULL,
                              updatedbyalias varchar(60) NULL,
                              siteid int4 NOT NULL,
                              lang bpchar(2) NOT NULL,
                              photo varchar(255) NULL,
                              CONSTRAINT category_alias_siteid_unique UNIQUE (alias, siteid),
                              CONSTRAINT category_pkey PRIMARY KEY (id),
                              CONSTRAINT category_parent_id_fk FOREIGN KEY (parent_id) REFERENCES cms.category(id) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT category_siteid_lang_fk FOREIGN KEY (siteid) REFERENCES cms.website(id)
);
CREATE INDEX category_ordering_idx ON cms.category USING btree (ordering);
CREATE INDEX category_status_idx ON cms.category USING btree (status);
CREATE INDEX category_type_idx ON cms.category USING btree (type);

CREATE TABLE page (
                      id SERIAL PRIMARY KEY,
                      category_id INTEGER NULL,
                      name VARCHAR(255) NULL,
                      alias VARCHAR(50) NULL,
                      photo VARCHAR(255) NULL,
                      description TEXT NULL,
                      intro TEXT NULL,
                      text TEXT NULL,
                      status VARCHAR(30) NULL,
                      ordering INTEGER NULL,
                      created TIMESTAMP NULL,
                      createdby INTEGER NULL,
                      createdbyalias VARCHAR(60) NULL,
                      updated TIMESTAMP NULL,
                      updatedby INTEGER NULL,
                      updatedbyalias VARCHAR(60) NULL,
                      siteid INTEGER NOT NULL,
                      lang CHAR(2) NOT NULL,
                      CONSTRAINT unique_alias_siteid UNIQUE (alias, siteid),
                      CONSTRAINT fk_category FOREIGN KEY (category_id)
                          REFERENCES category(id)
                          ON DELETE SET NULL
                          ON UPDATE CASCADE
);

-- Indexes
CREATE INDEX page_name_idx ON page (name);
CREATE INDEX page_ordering_idx ON page (ordering);
CREATE INDEX page_status_idx ON page (status);
CREATE INDEX page_created_idx ON page (created);

CREATE TABLE content (
                         id SERIAL PRIMARY KEY,
                         category_id INTEGER NULL,
                         categoryids VARCHAR(255) NULL,
                         type VARCHAR(30) NOT NULL,
                         name VARCHAR(255) NULL,
                         photo VARCHAR(255) NULL,
                         description TEXT NULL,
                         tags VARCHAR(255),
                         intro TEXT NULL,
                         text TEXT NULL, -- PostgreSQL-д MEDIUMTEXT дэмждэггүй тул TEXT болгож өөрчилсөн.
                         tags TEXT NULL,
                         status VARCHAR(30) NULL,
                         published TIMESTAMP NULL,
                         section_id INTEGER NULL,
                         created TIMESTAMP NULL,
                         createdby INTEGER NULL,
                         createdbyalias VARCHAR(60) NULL,
                         updated TIMESTAMP NULL,
                         updatedby INTEGER NULL,
                         updatedbyalias VARCHAR(60) NULL,
                         link VARCHAR(255) NULL,
                         siteid INTEGER NOT NULL,
                         lang CHAR(2) NOT NULL,
                         CONSTRAINT fk_category FOREIGN KEY (category_id)
                             REFERENCES category (id)
                             ON DELETE SET NULL
                             ON UPDATE CASCADE
);

-- Индексүүдийг нэмэх
CREATE INDEX content_name_idx ON content (name);
CREATE INDEX content_status_idx ON content (status);
CREATE INDEX content_type_idx ON content (type);
CREATE INDEX content_created_idx ON content (created);
CREATE INDEX content_published_idx ON content (published);
