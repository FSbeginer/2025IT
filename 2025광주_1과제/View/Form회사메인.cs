using _2025광주_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제.View
{
    public partial class Form회사메인 : _2025광주_1과제.Model.BF
    {
        List<applicationemployment> datas = new List<applicationemployment>();
        public Form회사메인()
        {
            InitializeComponent();
        }

        private void Form회사메인_Load(object sender, EventArgs e)
        {
            dataGridView1.RowTemplate.Height = 90;
            dataGridView1.ContextMenuStrip = contextMenuStrip1;
            string path = Hp.company.c_no == 4 ? "img.png" : Hp.company.c_no + ".png";
            pictureBox1.Image = Hp.GetImage("company/" + path);
            label1.Text = Hp.company.c_name;
            using (var db = new Model.placementEntities())
            {
                var list1 = db.occupation.Select(x => x.o_name).ToList();
                list1.Insert(0, "전체");
                comboBox1.DataSource = list1;
                var list2 = db.education.Select(x => x.e_name).ToList();
                list2.Insert(0, "전체");
                comboBox3.DataSource = list2;
                comboBox2.SelectedIndex = 0;
            }
            dataGridView1.ClearSelection();
            dataGridView1.CurrentCell = null;
        }

        private void getData()
        {
            Console.WriteLine(1);
            datas.Clear();
            dataGridView1.Rows.Clear();
            using (var db = new Model.placementEntities())
            {
                int c1 = comboBox1.SelectedIndex;
                int c2 = comboBox2.SelectedIndex;
                int c3 = comboBox3.SelectedIndex;
                var list = db.applicationemployment.Where(x => x.jobposting.c_no == Hp.company.c_no && (c1 == 0 ? true : x.user.o_no == c1)&&(c2==0? true : c2==1? x.user.u_situation == 1 : x.user.u_situation==0)&&(c3==0? true : c3==x.user.e_no)).ToList();
                datas = list;
                foreach (var item in list)
                {
                    dataGridView1.Rows.Add(dataGridView1.Rows.Count+1, Hp.GetImage("user/"+item.u_no+".png"), item.user.u_name, item.user.occupation.o_name, item.user.u_situation==0?"경력":"신입", item.user.education.e_name);
                }
                label2.Text = "지원자 수:" + datas.Count + "개";
            }
        }

        private void comboBox3_SelectedIndexChanged(object sender, EventArgs e)
        {
            getData();
        }

        private void 합격ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if(dataGridView1.CurrentCell == null)
            {
                Hp.msgErr("선택하지 않았습니다.");
                return;
            }
            using (var db = new Model.placementEntities())
            {
                var d = datas[dataGridView1.CurrentRow.Index];
                db.applicationemployment.Attach(d);
                employment em = new employment();
                em.u_no = Hp.user.u_no;
                em.c_no = d.jobposting.c_no;
                em.o_no = Hp.user.o_no;
                em.ep_date = DateTime.Now.Date;
                db.employment.Add(em);
                db.applicationemployment.Remove(d);
                db.SaveChanges();
            }
            getData();
        }

        private void 탈락ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (dataGridView1.CurrentCell == null)
            {
                Hp.msgErr("선택하지 않았습니다.");
                return;
            }
            using (var db = new Model.placementEntities())
            {
                var d = datas[dataGridView1.CurrentRow.Index];
                db.applicationemployment.Attach(d);
                db.applicationemployment.Remove(d);
                db.SaveChanges();
            }
            getData();
        }
    }
}
