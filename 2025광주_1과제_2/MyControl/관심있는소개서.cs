using _2025광주_1과제_2.Model;
using _2025광주_1과제_2.Template;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025광주_1과제_2.MyControl
{
    public partial class 관심있는소개서 : UserControl
    {
        public 관심있는소개서()
        {
            InitializeComponent();
        }

        List<selfintroduction> data = new List<selfintroduction>();
        private void 자기소개서내용보러가기ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (dataGridView1.SelectedRows.Count == 0)
            {
                Hp.msgErr("자기소개서를 선택하여 주세요.");
                return;
            }
            ((BF)FindForm()).showPage(new View.F_소개서내용 { si = data[dataGridView1.CurrentRow.Index] });
        }

        private void 관심있는소개서_Load(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                var silist = Hp.user.u_si.Split(',').Select(int.Parse).ToList();
                var list = db.selfintroduction.Where(x=>silist.Contains(x.si_no));
                foreach (var item in list)
                {
                    dataGridView1.Rows.Add(item.si_no, item.si_title, item.si_subtitle);
                }
                dataGridView1.ClearSelection();
            }
        }
    }
}
