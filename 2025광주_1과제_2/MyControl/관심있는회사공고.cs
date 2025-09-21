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
    public partial class 관심있는회사공고 : UserControl
    {
        public 관심있는회사공고()
        {
            InitializeComponent();
        }
        List<jobposting> data = new List<jobposting>();
        private void 관심있는회사공고_Load(object sender, EventArgs e)
        {
            using (var db =new Model.placementEntities())
            {
                var sclist = Hp.user.u_sc.Split(',').Select(int.Parse).ToList();
                var list = db.jobposting.Where(x=>sclist.Contains(x.c_no)&&x.end_date>DateTime.Today).ToList();
                foreach (var item in list)
                {
                    string path = item.c_no == 4 ? "img.png" : item.c_no + ".png";
                    dataGridView1.Rows.Add(item.jp_no, Hp.GetImage("company/"+path),item.company.c_name,item.company.c_information==0?"중소기업":item.company.c_information==1?"중견기업":"대기업", item.jp_title);
                    data.Add(item);
                }
                dataGridView1.ClearSelection();
                dataGridView1.ContextMenuStrip = contextMenuStrip1;

            }
        }

        private void 채용공고보러가기ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if(dataGridView1.SelectedRows.Count == 0)
            {
                Hp.msgErr("채용공고를 선택하여 주세요.");
                return;
            }
            ((BF)FindForm()).showPage(new View.D_채용공고() { jobposting = data[dataGridView1.CurrentRow.Index] });
        }
    }
}
