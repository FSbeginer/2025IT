using _2025광주_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025광주_1과제.Controls
{
    public partial class Control관심있회사 : UserControl
    {
        List<jobposting> datas;
        public Control관심있회사()
        {
            InitializeComponent();
        }

        private void Control관심있회사_Load(object sender, EventArgs e)
        {
            dataGridView1.RowTemplate.Height = 80;
            using (var db = new Model.placementEntities())
            {
                var list = Hp.user.u_sc.Split(',').Select(int.Parse).ToList();
                datas = db.jobposting.Where(x=>list.Contains(x.c_no)).ToList();
                foreach (var item in datas)
                {
                    string path = item.c_no == 4 ? "img.png" : item.c_no + ".png";
                    string type = item.company.c_information == 0 ? "중소기업" : item.company.c_information == 1 ? "중견기업" : "대기업";
                    dataGridView1.Rows.Add(item.jp_no, Hp.GetImage("company/"+path),item.company.c_name, type, item.jp_title);
                }
                dataGridView1.ClearSelection();
                dataGridView1.CurrentCell = null;
            }
            dataGridView1.ContextMenuStrip = contextMenuStrip1;
        }

        private void 채용공고보러가기ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (dataGridView1.CurrentRow == null)
            {
                Hp.msgErr("채용공고를 선택하여 주세요.");
                return;
            }
            (FindForm() as BF).showPage(new View.Form채용공고 { jobposting = datas[dataGridView1.CurrentRow.Index] });
        }
    }
}
