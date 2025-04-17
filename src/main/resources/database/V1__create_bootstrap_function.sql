
CREATE OR REPLACE FUNCTION CHK_TBL(p_table_name character varying)
RETURNS INTEGER AS $$
DECLARE 
    table_count INTEGER;
BEGIN
        SELECT count(*) into table_count
        FROM information_schema.tables 
        WHERE upper(table_name) = upper(p_table_name) and table_schema = current_schema();
        return table_count;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION CHK_COL(
    p_table_name character varying,
    p_column_name character varying
) RETURNS INTEGER AS $$
DECLARE
    column_count INTEGER;
BEGIN
    SELECT COUNT(*)
    INTO column_count
    FROM information_schema.columns
    WHERE upper(table_name) = upper(p_table_name)
      AND upper(column_name) = upper(p_column_name) and table_schema = current_schema();
    RETURN column_count;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION CHK_IX(
    p_index_name character varying
) RETURNS INTEGER AS $$
DECLARE
    index_count INTEGER;
BEGIN
    SELECT COUNT(*)
    INTO index_count
    FROM pg_indexes
    WHERE upper(indexname) = upper(p_index_name) and schemaname = current_schema();

    RETURN index_count;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION CHK_CNSTR(
    p_constraint_name character varying
) RETURNS INTEGER AS $$
DECLARE
    constraint_count INTEGER;
BEGIN
    SELECT COUNT(*)
    INTO constraint_count
    FROM information_schema.table_constraints
    WHERE upper(constraint_name) = upper(p_constraint_name) and constraint_schema = current_schema();
    RETURN constraint_count;
END;
$$ LANGUAGE plpgsql;
