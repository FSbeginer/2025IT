using _2025경기_1과제.Model;
using _2025경기_1과제.Template;
using _2025경기_1과제.View;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025경기_1과제.UserControls
{
    public partial class MainControl : UserControl
    {
        public movie Movie { get; set; }
        public MainControl()
        {
            InitializeComponent();
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            (FindForm() as BF).ShowPage(new MovieInfo { Movie = Movie });
        }

        private void MainControl_Load(object sender, EventArgs e)
        {
            if(Hp.GetAge(Hp.user.birth)>=Movie.age.age1)
                pictureBox1.Image = Hp.GetImage("Image/"+Movie.mno+".jpg");
            else
                pictureBox1.Image = Hp.GetGrayImage(Hp.GetImage("Image/" + Movie.mno + ".jpg"));
            label1.Text = Movie.mname;

        }
    }
}
