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
    public partial class Control관심있자소서 : UserControl
    {
        List<selfintroduction> datas;
        public Control관심있자소서()
        {
            InitializeComponent();
        }

        private void Control관심있자소서_Load(object sender, EventArgs e)
        {
            dataGridView1.RowTemplate.Height = 40;

            using (var db = new Model.placementEntities())
            {
                var list = Hp.user.u_si.Split(',').Select(int.Parse).ToList();
                datas = db.selfintroduction.AsEnumerable().Where(x => list.Contains(x.si_no)).ToList();
                foreach (var item in datas)
                {
                    dataGridView1.Rows.Add(item.si_no, item.si_title, item.si_subtitle);
                }
            }
            dataGridView1.ContextMenuStrip = contextMenuStrip1;
            dataGridView1.ClearSelection();
            dataGridView1.CurrentCell = null;
        }

        private void 자기소개서내용보러가기ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (dataGridView1.CurrentRow == null)
            {
                Hp.msgErr("자기소개서를 선택하여 주세요.");

                return;
            }

            (FindForm() as BF).showPage(new View.Form소개서내용 { selfintroduction = datas[dataGridView1.CurrentRow.Index] });
        }
    }
}
