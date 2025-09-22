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
    public partial class Control소개서내용 : UserControl
    {
        public comment comment { get; set; }
        public bool delete { get; set; }
        public Control소개서내용()
        {
            InitializeComponent();
        }

        private void Control소개서내용_Load(object sender, EventArgs e)
        {
            label1.Text = "작성자 : " + comment.user.u_name;
            label2.Text = comment.cm_date.ToString("yyyy. MM. dd");
            label3.Text = "댓글 : " + comment.cm_explan;
        }

        private void label2_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            if (delete)
            {
                using (var db = new Model.placementEntities())
                {
                    db.comment.Attach(comment);
                    db.comment.Remove(comment);
                    db.SaveChanges();

                    (FindForm() as View.Form댓글확인).getdata();
                }
            }
        }
    }
}
