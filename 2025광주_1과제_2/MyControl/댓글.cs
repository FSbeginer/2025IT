using _2025광주_1과제_2.Model;
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
    public partial class 댓글 : UserControl
    {
        public  comment comment { get; set; }
        public bool delete { get; set; }
        public 댓글()
        {
            InitializeComponent();

        }

        private void 댓글_Load(object sender, EventArgs e)
        {
            label1.Text = "작성자 : "+comment.user.u_name;
            label2.Text = "댓글 : " + comment.cm_explan;
            label3.Text = comment.cm_date.ToString("yyyy. MM. dd");
        }

        private void label2_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            if (delete)
            {
                using (var db= new Model.placementEntities())
                {
                    var c = db.comment.Find(comment.cm_no);
                    db.comment.Remove(c);
                    db.SaveChanges();
                    Hp.msgInfo("댓글을 삭제하였습니다.");
                    ((View.I_댓글확인)FindForm()).panelLoad();
                }
            }
        }
    }
}
