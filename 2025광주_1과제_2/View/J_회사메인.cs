using _2025광주_1과제_2.Model;
using _2025광주_1과제_2.MyControl;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제_2.View
{
    public partial class J_회사메인 : _2025광주_1과제_2.Template.BF
    {
        public J_회사메인()
        {
            InitializeComponent();
        }

        private void J_회사메인_Load(object sender, EventArgs e)
        {
            dataLoad();
            dataGridView1.ContextMenuStrip = contextMenuStrip1;

            string path = Hp.company.c_no == 4 ? "img.png" : Hp.company.c_no + ".png";
            pictureBox1.Image = GetImage("company/" + path);
            label1.Text = Hp.company.c_name;

            using (var db = new Model.placementEntities())
            {
                var list1 = db.occupation.Select(x => x.o_name).ToList();
                list1.Insert(0, "전체");
                comboBox1.DataSource = list1;
                comboBox2.SelectedIndex = 0;
                var list2 = db.education.Select(x => x.e_name).ToList();
                list2.Insert(0, "전체");
                comboBox3.DataSource = list2;
            }
        }

        List<applicationemployment> aplist = new List<applicationemployment>();
        private void dataLoad()
        {
            dataGridView1.Rows.Clear();
            aplist.Clear();
            using (var db = new Model.placementEntities())
            {
                var list = db.applicationemployment.Where(x => x.jobposting.c_no == Hp.company.c_no).ToList();
                if (comboBox1.SelectedIndex != 0)
                {
                    list = list.Where(x => x.jobposting.o_no == comboBox1.SelectedIndex).ToList();
                }
                if (comboBox2.SelectedIndex != 0)
                {
                    int sit = comboBox2.SelectedIndex == 1 ? 1 : 0;
                    list = list.Where(x => x.user.u_situation == sit).ToList();
                }
                if (comboBox3.SelectedIndex != 0)
                {
                    list = list.Where(x => x.user.e_no == comboBox3.SelectedIndex).ToList();
                }

                foreach (var item in list)
                {
                    dataGridView1.Rows.Add(dataGridView1.Rows.Count + 1, GetImage("user/" + item.u_no + ".png"), item.user.u_name, item.user.occupation.o_name, item.user.u_situation == 0 ? "경력" : "신입", item.user.education.e_name);
                    aplist.Add(item);
                }
                label2.Text = "지원자 수 : " + db.applicationemployment.Where(x => x.jobposting.c_no == Hp.company.c_no).Count() + "개";
            }

            dataGridView1.ClearSelection();
            dataGridView1.CurrentCell = null;
        }

        private void 합격ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (dataGridView1.CurrentCell != null)
            {
                using (var db = new Model.placementEntities())
                {
                    var ap = aplist[dataGridView1.CurrentRow.Index];
                    var app = db.applicationemployment.Find(ap.ae_no);
                    employment ep = new employment();
                    ep.u_no = app.u_no;
                    ep.c_no = Hp.company.c_no;
                    ep.o_no = app.user.o_no;
                    ep.ep_date = DateTime.Today;
                    db.employment.Add(ep);
                    db.applicationemployment.Remove(app);
                    db.SaveChanges();
                    dataLoad();
                }
            }
            else
            {
                msgErr("선택하지 않았습니다.");
            }
        }

        private void 탈락ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (dataGridView1.CurrentCell != null)
            {
                using (var db = new Model.placementEntities())
                {
                    var ap = aplist[dataGridView1.CurrentRow.Index];
                    var app = db.applicationemployment.Find(ap.ae_no);
                    db.applicationemployment.Remove(app);
                    db.SaveChanges();
                    dataLoad();
                }
            }
            else
            {
                msgErr("선택하지 않았습니다.");
            }

        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            dataLoad();
        }
    }
}
