<?php
class M_pengaduan1 extends CI_Model {

	public function __construct(){
		parent::__construct();
		$this->load->database();
	}

	function tampil_data(){
		$sql = "SELECT tabel_pengaduan.id_pengaduan, tabel_pengaduan.tanggal_pengaduan,tabel_pengaduan.isi_pengaduan, tabel_pengaduan.status, tabel_user.nama_user, tabel_user.alamat, tabel_user.telp FROM tabel_pengaduan INNER JOIN tabel_user ON tabel_user.id_user=tabel_pengaduan.id_user WHERE tabel_pengaduan.status = 0";
		$data = $this->db->query($sql);

		return $data->result();
	}
    function up_status($table, $where, $id, $data){
        
        $this->db->where($where,$id);
		$this->db->update($table,$data);
    }
	


}
?>
