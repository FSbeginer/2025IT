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

namespace _2025광주_1과제_2.Model
{
    public partial class 소개서 : UserControl
    {
        public selfintroduction intro { get; set; }
        public 소개서()
        {
            InitializeComponent();
        }

        private void label1_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            ((BF)FindForm()).showPage(new View.F_소개서내용() { si = intro});
        }

        private void 소개서_Load(object sender, EventArgs e)
        {
            label1.Text = intro.si_subtitle;
            label2.Text = intro.si_title;
            label3.Text = intro.user.u_situation == 0 ? "경력" : "신입";
            label4.Text = intro.occupation.o_name;

        }
    }
}
