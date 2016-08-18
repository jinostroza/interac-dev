CREATE OR REPLACE FUNCTION insert_analitics() RETURNS void AS $$
  
BEGIN
  INSERT INTO analiticalocal(
            camara_date, slider_inicio, slider_fin, slider_img, 
            genero, edad, expresion, id_modulo)
    SELECT cl.fec_captura,sl.fec_inicio_slide, sl.fec_fin_slide, sl.contenido_name,cl.genero,cl.edad,  cl.expresion, cl.id_modulo  
  FROM camara_log cl,slider_log sl WHERE cl.fec_captura>=sl.fec_inicio_slide and cl.fec_captura<sl.fec_fin_slide and cl.id_modulo=sl.id_modulo and cl.estado='SinProcesar';
  UPDATE camara_log SET estado='Procesado' WHERE estado='SinProcesar';
  
END;
$$ LANGUAGE plpgsql;

SELECT insert_analitics();
UPDATE camara_log SET estado='Procesado' WHERE estado='SinProcesar';
CREATE OR REPLACE FUNCTION tr_insert_analitica()
RETURNS trigger AS
$BODY$
begin
perform recorrer_cursor_for(); 
return new;
end
$BODY$
LANGUAGE plpgsql;


CREATE TRIGGER insert_to_remote
  AFTER INSERT
  ON analiticalocal
  FOR EACH ROW
  EXECUTE PROCEDURE tr_insert_analitica();